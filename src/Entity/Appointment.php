<?php

namespace App\Entity;

use DateTimeInterface;
use Doctrine\ORM\Mapping as ORM;


#[ORM\Entity]
class Appointment
{

    #[ORM\Id]
    #[ORM\Column(type: "integer")]
    private int $id;

    #[ORM\Column(type: "integer")]
    private int $appointment_id;

    #[ORM\Column(type: "bigint")]
    private string $patient_id;

    #[ORM\Column(type: "string", length: 100)]
    private string $name;

    #[ORM\Column(type: "string", length: 50)]
    private string $gender;

    #[ORM\Column(type: "string", length: 200)]
    private string $description;

    #[ORM\Column(type: "string", length: 255)]
    private string $diagnosis;

    #[ORM\Column(type: "string", length: 255)]
    private string $treatment;

    #[ORM\Column(type: "bigint")]
    private string $mobile_number;

    #[ORM\Column(type: "string", length: 500)]
    private string $address;

    #[ORM\Column(type: "date")]
    private DateTimeInterface $date;

    #[ORM\Column(type: "date")]
    private DateTimeInterface $date_modify;

    #[ORM\Column(type: "date")]
    private DateTimeInterface $date_delete;

    #[ORM\Column(type: "string", length: 50)]
    private string $status;

    #[ORM\Column(type: "string", length: 50)]
    private string $doctor;

    #[ORM\Column(type: "string", length: 100)]
    private string $specialized;

    #[ORM\Column(type: "date")]
    private DateTimeInterface $schedule;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return int
     */
    public function getAppointmentId(): int
    {
        return $this->appointment_id;
    }

    /**
     * @param int $appointment_id
     */
    public function setAppointmentId(int $appointment_id): void
    {
        $this->appointment_id = $appointment_id;
    }

    /**
     * @return string
     */
    public function getPatientId(): string
    {
        return $this->patient_id;
    }

    /**
     * @param string $patient_id
     */
    public function setPatientId(string $patient_id): void
    {
        $this->patient_id = $patient_id;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName(string $name): void
    {
        $this->name = $name;
    }

    /**
     * @return string
     */
    public function getGender(): string
    {
        return $this->gender;
    }

    /**
     * @param string $gender
     */
    public function setGender(string $gender): void
    {
        $this->gender = $gender;
    }

    /**
     * @return string
     */
    public function getDescription(): string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getDiagnosis(): string
    {
        return $this->diagnosis;
    }

    /**
     * @param string $diagnosis
     */
    public function setDiagnosis(string $diagnosis): void
    {
        $this->diagnosis = $diagnosis;
    }

    /**
     * @return string
     */
    public function getTreatment(): string
    {
        return $this->treatment;
    }

    /**
     * @param string $treatment
     */
    public function setTreatment(string $treatment): void
    {
        $this->treatment = $treatment;
    }

    /**
     * @return string
     */
    public function getMobileNumber(): string
    {
        return $this->mobile_number;
    }

    /**
     * @param string $mobile_number
     */
    public function setMobileNumber(string $mobile_number): void
    {
        $this->mobile_number = $mobile_number;
    }

    /**
     * @return string
     */
    public function getAddress(): string
    {
        return $this->address;
    }

    /**
     * @param string $address
     */
    public function setAddress(string $address): void
    {
        $this->address = $address;
    }

    /**
     * @return DateTimeInterface
     */
    public function getDate(): DateTimeInterface
    {
        return $this->date;
    }

    /**
     * @param DateTimeInterface $date
     */
    public function setDate(DateTimeInterface $date): void
    {
        $this->date = $date;
    }

    /**
     * @return DateTimeInterface
     */
    public function getDateModify(): DateTimeInterface
    {
        return $this->date_modify;
    }

    /**
     * @param DateTimeInterface $date_modify
     */
    public function setDateModify(DateTimeInterface $date_modify): void
    {
        $this->date_modify = $date_modify;
    }

    /**
     * @return DateTimeInterface
     */
    public function getDateDelete(): DateTimeInterface
    {
        return $this->date_delete;
    }

    /**
     * @param DateTimeInterface $date_delete
     */
    public function setDateDelete(DateTimeInterface $date_delete): void
    {
        $this->date_delete = $date_delete;
    }

    /**
     * @return string
     */
    public function getStatus(): string
    {
        return $this->status;
    }

    /**
     * @param string $status
     */
    public function setStatus(string $status): void
    {
        $this->status = $status;
    }

    /**
     * @return string
     */
    public function getDoctor(): string
    {
        return $this->doctor;
    }

    /**
     * @param string $doctor
     */
    public function setDoctor(string $doctor): void
    {
        $this->doctor = $doctor;
    }

    /**
     * @return string
     */
    public function getSpecialized(): string
    {
        return $this->specialized;
    }

    /**
     * @param string $specialized
     */
    public function setSpecialized(string $specialized): void
    {
        $this->specialized = $specialized;
    }

    /**
     * @return DateTimeInterface
     */
    public function getSchedule(): DateTimeInterface
    {
        return $this->schedule;
    }

    /**
     * @param DateTimeInterface $schedule
     */
    public function setSchedule(DateTimeInterface $schedule): void
    {
        $this->schedule = $schedule;
    }

    public function __toString(): string
    {
        return $this->name;
    }
}
