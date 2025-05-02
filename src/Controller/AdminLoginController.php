<?php

namespace App\Controller;

use App\Entity\Admin;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

final class AdminLoginController extends AbstractController
{
    #[Route('/admin/adminLogin', name: 'app_admin_login')]
    public function login(Request $request, EntityManagerInterface $em, SessionInterface $session): Response
    {
        $error = null;
    
        if ($request->isMethod('POST')) {
            $username = $request->request->get('username');
            $password = $request->request->get('password');
    
            $admin = $em->getRepository(Admin::class)->findOneBy(['username' => $username]);
    
            if ($admin && $admin->getPassword() === $password) {
                $session->set('admin_id', $admin->getAdmin_id());
                return $this->redirectToRoute('app_admin_dashboard'); 
            }
    
            $error = 'Invalid username or password.';
        }
    
        return $this->render('admin_login/index.html.twig', [
            'error' => $error,
        ]);
    }
    
}