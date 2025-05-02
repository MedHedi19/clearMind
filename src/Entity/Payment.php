<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;


#[ORM\Entity]
class Payment
{

    #[ORM\Id]
    #[ORM\Column(type: "integer")]
    private int $Id;

    #[ORM\Column(type: "integer")]
    private int $patient_id;

    #[ORM\Column(type: "string", length: 250)]
    private string $doctor;

    #[ORM\Column(type: "float")]
    private float $total_days;

    #[ORM\Column(type: "float")]
    private float $total_price;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $date;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $date_checkout;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $date_pay;

    public function getId()
    {
        return $this->Id;
    }

    public function setId($value)
    {
        $this->Id = $value;
    }

    public function getPatient_id()
    {
        return $this->patient_id;
    }

    public function setPatient_id($value)
    {
        $this->patient_id = $value;
    }

    public function getDoctor()
    {
        return $this->doctor;
    }

    public function setDoctor($value)
    {
        $this->doctor = $value;
    }

    public function getTotal_days()
    {
        return $this->total_days;
    }

    public function setTotal_days($value)
    {
        $this->total_days = $value;
    }

    public function getTotal_price()
    {
        return $this->total_price;
    }

    public function setTotal_price($value)
    {
        $this->total_price = $value;
    }

    public function getDate()
    {
        return $this->date;
    }

    public function setDate($value)
    {
        $this->date = $value;
    }

    public function getDate_checkout()
    {
        return $this->date_checkout;
    }

    public function setDate_checkout($value)
    {
        $this->date_checkout = $value;
    }

    public function getDate_pay()
    {
        return $this->date_pay;
    }

    public function setDate_pay($value)
    {
        $this->date_pay = $value;
    }
}
