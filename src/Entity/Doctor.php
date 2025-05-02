<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;


#[ORM\Entity]
class Doctor
{

    #[ORM\Id]
    #[ORM\Column(type: "integer")]
    private int $id;

    #[ORM\Column(type: "string", length: 50)]
    private string $doctor_id;

    #[ORM\Column(type: "string", length: 100)]
    private string $password;

    #[ORM\Column(type: "string", length: 100)]
    private string $full_name;

    #[ORM\Column(type: "string", length: 100)]
    private string $email;

    #[ORM\Column(type: "string", length: 100)]
    private string $gender;

    #[ORM\Column(type: "bigint")]
    private string $mobile_number;

    #[ORM\Column(type: "string", length: 100)]
    private string $specialized;

    #[ORM\Column(type: "string", length: 200)]
    private string $address;

    #[ORM\Column(type: "string", length: 500)]
    private string $image;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $date;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $modify_date;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $delete_date;

    #[ORM\Column(type: "string", length: 50)]
    private string $status;

    public function getId()
    {
        return $this->id;
    }

    public function setId($value)
    {
        $this->id = $value;
    }

    public function getDoctor_id()
    {
        return $this->doctor_id;
    }

    public function setDoctor_id($value)
    {
        $this->doctor_id = $value;
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function setPassword($value)
    {
        $this->password = $value;
    }

    public function getFull_name()
    {
        return $this->full_name;
    }

    public function setFull_name($value)
    {
        $this->full_name = $value;
    }

    public function getEmail()
    {
        return $this->email;
    }

    public function setEmail($value)
    {
        $this->email = $value;
    }

    public function getGender()
    {
        return $this->gender;
    }

    public function setGender($value)
    {
        $this->gender = $value;
    }

    public function getMobile_number()
    {
        return $this->mobile_number;
    }

    public function setMobile_number($value)
    {
        $this->mobile_number = $value;
    }

    public function getSpecialized()
    {
        return $this->specialized;
    }

    public function setSpecialized($value)
    {
        $this->specialized = $value;
    }

    public function getAddress()
    {
        return $this->address;
    }

    public function setAddress($value)
    {
        $this->address = $value;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($value)
    {
        $this->image = $value;
    }

    public function getDate()
    {
        return $this->date;
    }

    public function setDate($value)
    {
        $this->date = $value;
    }

    public function getModify_date()
    {
        return $this->modify_date;
    }

    public function setModify_date($value)
    {
        $this->modify_date = $value;
    }

    public function getDelete_date()
    {
        return $this->delete_date;
    }

    public function setDelete_date($value)
    {
        $this->delete_date = $value;
    }

    public function getStatus()
    {
        return $this->status;
    }

    public function setStatus($value)
    {
        $this->status = $value;
    }
}
