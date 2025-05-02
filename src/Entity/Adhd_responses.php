<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;


#[ORM\Entity]
class Adhd_responses
{

    #[ORM\Id]
    #[ORM\Column(type: "integer")]
    private int $id;

    #[ORM\Column(type: "string", length: 50)]
    private string $category;

    #[ORM\Column(type: "boolean")]
    private bool $pregnancy_complicated;

    #[ORM\Column(type: "boolean")]
    private bool $premature_birth;

    #[ORM\Column(type: "boolean")]
    private bool $birth_complications;

    #[ORM\Column(type: "boolean")]
    private bool $learning_delay;

    #[ORM\Column(type: "boolean")]
    private bool $hyperactive;

    #[ORM\Column(type: "boolean")]
    private bool $sleep_issues;

    #[ORM\Column(type: "boolean")]
    private bool $family_tensions;

    #[ORM\Column(type: "boolean")]
    private bool $family_adhd;

    #[ORM\Column(type: "boolean")]
    private bool $screen_time;

    #[ORM\Column(type: "boolean")]
    private bool $concentration_issue;

    #[ORM\Column(type: "boolean")]
    private bool $loses_items;

    #[ORM\Column(type: "boolean")]
    private bool $impatience;

    #[ORM\Column(type: "boolean")]
    private bool $impulsive;

    #[ORM\Column(type: "boolean")]
    private bool $emotional_issues;

    #[ORM\Column(type: "integer")]
    private int $user_id;

    public function getId()
    {
        return $this->id;
    }

    public function setId($value)
    {
        $this->id = $value;
    }

    public function getCategory()
    {
        return $this->category;
    }

    public function setCategory($value)
    {
        $this->category = $value;
    }

    public function getPregnancy_complicated()
    {
        return $this->pregnancy_complicated;
    }

    public function setPregnancy_complicated($value)
    {
        $this->pregnancy_complicated = $value;
    }

    public function getPremature_birth()
    {
        return $this->premature_birth;
    }

    public function setPremature_birth($value)
    {
        $this->premature_birth = $value;
    }

    public function getBirth_complications()
    {
        return $this->birth_complications;
    }

    public function setBirth_complications($value)
    {
        $this->birth_complications = $value;
    }

    public function getLearning_delay()
    {
        return $this->learning_delay;
    }

    public function setLearning_delay($value)
    {
        $this->learning_delay = $value;
    }

    public function getHyperactive()
    {
        return $this->hyperactive;
    }

    public function setHyperactive($value)
    {
        $this->hyperactive = $value;
    }

    public function getSleep_issues()
    {
        return $this->sleep_issues;
    }

    public function setSleep_issues($value)
    {
        $this->sleep_issues = $value;
    }

    public function getFamily_tensions()
    {
        return $this->family_tensions;
    }

    public function setFamily_tensions($value)
    {
        $this->family_tensions = $value;
    }

    public function getFamily_adhd()
    {
        return $this->family_adhd;
    }

    public function setFamily_adhd($value)
    {
        $this->family_adhd = $value;
    }

    public function getScreen_time()
    {
        return $this->screen_time;
    }

    public function setScreen_time($value)
    {
        $this->screen_time = $value;
    }

    public function getConcentration_issue()
    {
        return $this->concentration_issue;
    }

    public function setConcentration_issue($value)
    {
        $this->concentration_issue = $value;
    }

    public function getLoses_items()
    {
        return $this->loses_items;
    }

    public function setLoses_items($value)
    {
        $this->loses_items = $value;
    }

    public function getImpatience()
    {
        return $this->impatience;
    }

    public function setImpatience($value)
    {
        $this->impatience = $value;
    }

    public function getImpulsive()
    {
        return $this->impulsive;
    }

    public function setImpulsive($value)
    {
        $this->impulsive = $value;
    }

    public function getEmotional_issues()
    {
        return $this->emotional_issues;
    }

    public function setEmotional_issues($value)
    {
        $this->emotional_issues = $value;
    }

    public function getUser_id()
    {
        return $this->user_id;
    }

    public function setUser_id($value)
    {
        $this->user_id = $value;
    }
}
