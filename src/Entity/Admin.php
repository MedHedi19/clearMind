<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;


#[ORM\Entity]
class Admin
{

    #[ORM\Id]
    #[ORM\Column(type: "integer")]
    private int $admin_id;

    #[ORM\Column(type: "string", length: 100)]
    private string $email;

    #[ORM\Column(type: "string", length: 100)]
    private string $username;

    #[ORM\Column(type: "string", length: 100)]
    private string $password;

    #[ORM\Column(type: "string", length: 100)]
    private string $full_name;

    #[ORM\Column(type: "string", length: 500)]
    private string $image;

    #[ORM\Column(type: "string", length: 50)]
    private string $gender;

    #[ORM\Column(type: "date")]
    private \DateTimeInterface $date;

    public function getAdmin_id()
    {
        return $this->admin_id;
    }

    public function setAdmin_id($value)
    {
        $this->admin_id = $value;
    }

    public function getEmail()
    {
        return $this->email;
    }

    public function setEmail($value)
    {
        $this->email = $value;
    }

    public function getUsername()
    {
        return $this->username;
    }

    public function setUsername($value)
    {
        $this->username = $value;
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

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($value)
    {
        $this->image = $value;
    }

    public function getGender()
    {
        return $this->gender;
    }

    public function setGender($value)
    {
        $this->gender = $value;
    }

    public function getDate()
    {
        return $this->date;
    }

    public function setDate($value)
    {
        $this->date = $value;
    }
}
