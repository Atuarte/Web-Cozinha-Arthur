package com.example.primeiroAppSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {
    @GetMapping("/cadastro")
    public String exibirCadastro(Model model){
        //Criando formulário vazio
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina","Cadastro");
        model.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque de Cozinha");

        return "cadastro";
    }
    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioForm form, Model model){
        if(!form.getSenha().equals(form.getConfirmarSenha())){
            model.addAttribute("erro", "Erro, as senhas não correspondem");
            return "cadastro";
        }
        IO.println("Usuário criado com sucesso!");
        IO.println(form.getNome()+" "+form.getEmail());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String exibirLogin(Model model){
        //Criando formulário vazio
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina","Login");
        model.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque de Cozinha");

        return "login";
    }
    @PostMapping("/login")
    public String processarLogin(@ModelAttribute UsuarioForm form, Model model){
        if(form.getEmail().endsWith("@df.senac.br")){
            return "redirect:/";
        }
        model.addAttribute("erro", "E-mail ou senha inválidos");
        return "login";
    }

    @GetMapping("/alterarSenha")
    public String exibirAlterarSenha(Model model){
        //Criando formulário vazio
        model.addAttribute("usuarioForm", new UsuarioForm());

        model.addAttribute("tituloPagina","Alterar Senha");
        model.addAttribute("subTituloPagina", "Sistema de Gerenciamento de Estoque de Cozinha");

        return "alterarSenha";
    }
    @PostMapping("/alterarSenha")
    public String processarAlterarSenha(@ModelAttribute UsuarioForm form, Model model){
        if(!form.getSenha().equals(form.getConfirmarSenha())){
            model.addAttribute("erro", "Erro, as senhas não correspondem");
            return "alterarSenha";
        }
        model.addAttribute("sucesso", "Senha alterada com sucesso!");

        return "redirect:/login";
    }
}