<?php

namespace App\Controller;

use App\Entity\Appointment;
use App\Form\AppointmentType;
use App\Repository\AppointmentRepository;
use App\Repository\DoctorRepository;
use App\Repository\PatientRepository;
use Doctrine\ORM\EntityManagerInterface;
use Psr\Log\LoggerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Attribute\Route;

final class NewAppointmentController extends AbstractController
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

    #[Route('/doctor/appointment/new', name: 'app_appointment_new')]
    public function index(
        Request $request,
        SessionInterface $session,
        EntityManagerInterface $em,
        LoggerInterface $logger
    ): Response {
        $doctorId = $session->get('doctor_id');
        $doctor = $this->doctorRepository->find($doctorId);
        $logger->info('Doctor ID in session: ' . var_export($doctorId, true));

        if (!$doctor) {
            $this->addFlash('error', 'Doctor not found.');
            return $this->redirectToRoute('app_doctor_login');
        }

        $appointment = new Appointment();

        // Auto-increment logic
        $maxId = $this->appointmentRepository->createQueryBuilder('a')
            ->select('MAX(a.appointment_id)')
            ->getQuery()
            ->getSingleScalarResult();
        $appointment->setAppointmentId((int) $maxId + 1);

        // Get only patients related to this doctor
        $patients = $this->patientRepository->findBy(['doctor' => 0]);

        $form = $this->createForm(AppointmentType::class, $appointment, [
            'patients' => $patients,
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $patientId = $form->get('patient_id')->getData();
            $patient = $this->patientRepository->findOneBy(['patient_id' => $patientId]);

            if (!$patient) {
                $this->addFlash('error', 'Patient not found.');
                return $this->redirectToRoute('app_doctor_dashboard');
            }

            // Populate appointment data
            $appointment->setPatientId($patient->getPatientId());
            $appointment->setDoctor("0");
            $appointment->setId((int) $maxId + 1);

            $appointment->setDateModify(new \DateTime());
            $appointment->setDateDelete(new \DateTime());
            $appointment->setDate($form->get('schedule')->getData());
            if($form->get('description')->getData() != null){
                $appointment->setDescription($form->get('description')->getData());
            }
            //shedule
            if($form->get('schedule')->getData() != null){
                $appointment->setSchedule($form->get('schedule')->getData());
            }else
            {
                $appointment->setSchedule(new \DateTime());
            }
            $appointment->setStatus('Waiting');
            $appointment->setName($patient->getFullName());
            $appointment->setSpecialized($doctor->getSpecialized());
            $appointment->setAddress($doctor->getAddress());
            $appointment->setMobileNumber($patient->getMobileNumber());
            $appointment->setGender($patient->getGender());

            $em->persist($appointment);
            $em->flush();

            $this->addFlash('success', 'Appointment created successfully.');
            return $this->redirectToRoute('app_doctor_dashboard');
        }

        return $this->render('doctor_dashboard/appointment/new.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}
