<?php

namespace App\Controller;

use App\Entity\Doctor;
use App\Entity\Patient;
use App\Repository\AppointmentRepository;
use App\Repository\DoctorRepository;
use App\Repository\PatientRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Attribute\Route;

final class DoctorDashboardController extends AbstractController
{
    private AppointmentRepository $appointmentRepository;
    private DoctorRepository $doctorRepository;
    private PatientRepository $patientRepository;

    public function __construct(
        AppointmentRepository $appointmentRepository,
        DoctorRepository $doctorRepository,
        PatientRepository $patientRepository
    ) {
        $this->appointmentRepository = $appointmentRepository;
        $this->doctorRepository = $doctorRepository;
        $this->patientRepository = $patientRepository;
    }

    #[Route('/doctor/doctorDashboard', name: 'app_doctor_dashboard')]
    public function index(SessionInterface $session): Response
    {
        $doctorId = $session->get('doctor_id');
//        if (!$doctorId) {
//            return $this->redirectToRoute('app_doctor_login');
//        }

        $doctor = $this->doctorRepository->find($doctorId);
        if (!$doctor) {
            throw $this->createNotFoundException('Doctor not found');
        }

        $appointments = $this->appointmentRepository->findBy([], ['date' => 'DESC']);
        $patients = $this->patientRepository->findBy(['doctor' => $doctor]);
        $patientCount = count($patients);
        $activePatients = count(array_filter($patients, fn($p) => $p->getStatus() === 'Active'));
        $inactivePatients = $patientCount - $activePatients;

        $appointmentDates = [];
        $appointmentCounts = [];
        $patientDates = [];
        //collect the dates of the appointments with patients
        $patientDates = array_map(fn($p) => $p->getDate()->format('Y-m-d'), $patients);
        $patientCounts = [];

        foreach ($appointments as $appointment) {
            $dateKey = $appointment->getDate()->format('Y-m-d');
            $appointmentCounts[$dateKey] = ($appointmentCounts[$dateKey] ?? 0) + 1;
        }

        foreach ($patients as $patient) {
            $dateKey = $patient->getDate()->format('Y-m-d');
            $patientCounts[$dateKey] = ($patientCounts[$dateKey] ?? 0) + 1;
        }

        ksort($appointmentCounts);
        ksort($patientCounts);

        return $this->render('doctor_dashboard/index.html.twig', [
            'appointments' => $appointments,
            'doctor' => $doctor,
            'patientCount' => $patientCount,
            'activePatients' => $activePatients,
            'inactivePatients' => $inactivePatients,
            'appointmentDates' => array_keys($appointmentCounts),
            'appointmentCounts' => array_values($appointmentCounts),
            'patientDates' => array_keys($patientCounts),
            'patientCounts' => array_values($patientCounts),
        ]);
    }
}
