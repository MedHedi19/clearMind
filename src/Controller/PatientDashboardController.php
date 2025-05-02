<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class PatientDashboardController extends AbstractController
{
    #[Route('/patient/patientDashboard', name: 'app_patient_dashboard')]
    public function index(): Response
    {
        return $this->render('patient_dashboard/index.html.twig', [
            'controller_name' => 'PatientDashboardController',
        ]);
    }
}