<?php

// src/Form/AppointmentType.php

namespace App\Form;

use App\Entity\Appointment;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\OptionsResolver\OptionsResolver;

final class AppointmentType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('appointment_id', IntegerType::class, [
                'label' => 'Appointment ID',
                'attr' => ['readonly' => true],
            ])
            ->add('patient_id', ChoiceType::class, [
                'label' => 'Patient',
                'choices' => $options['patients'],
                'choice_label' => fn($p) => $p->getFullName(),
                'choice_value' => fn($p) => $p ? (string) $p->getPatientId() : '',
                'mapped' => false, // 🚨 critical fix
            ])
            ->add('description', TextType::class)
            ->add('diagnosis', TextType::class)
            ->add('treatment', TextType::class)
            ->add('schedule', DateType::class, ['widget' => 'single_text']);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Appointment::class,
            'patients' => [],
        ]);
    }
}
