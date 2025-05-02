<?php

namespace App\Controller;

use App\Entity\Patient;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class PatientLoginController extends AbstractController
{
    #[Route('patient/patientLogin', name: 'app_patient_login')]
    public function login(Request $request, EntityManagerInterface $em, SessionInterface $session): Response
    {
        $error = null;

        if ($request->isMethod('POST')) {
            $patientId = $request->request->get('patient_id');
            $password = $request->request->get('password');

            $patient = $em->getRepository(Patient::class)->findOneBy(['patient_id' => $patientId]);

            if ($patient && $patient->getPassword() === $password) {
                $session->set('patient_id', $patient->getId());
                return $this->redirectToRoute('app_patient_dashboard');
            }

            $error = 'Invalid patient ID or password.';
        }

        return $this->render('patient_login/index.html.twig', [
            'error' => $error,
        ]);
    }

}