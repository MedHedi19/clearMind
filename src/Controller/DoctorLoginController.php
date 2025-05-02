<?php

namespace App\Controller;

use App\Entity\Doctor;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

final class DoctorLoginController extends AbstractController
{
    #[Route('/doctor/doctorLogin', name: 'app_doctor_login')]
    public function login(Request $request, EntityManagerInterface $em, SessionInterface $session): Response
    {
        $error = null;

        if ($request->isMethod('POST')) {
            $doctorId = $request->request->get('doctor_id');
            $password = $request->request->get('password');

            $doctor = $em->getRepository(Doctor::class)->findOneBy(['doctor_id' => $doctorId]);

            if ($doctor && $doctor->getPassword() === $password) {
                $session->set('doctor_id', $doctor->getId());
                return $this->redirectToRoute('app_doctor_dashboard'); // Make sure this route exists
            }

            $error = 'Invalid Doctor ID or password.';
        }

        return $this->render('doctor_login/index.html.twig', [
            'error' => $error,
        ]);
    }
}