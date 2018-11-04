package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	@Autowired
	private FilmeService fService;
	@Autowired
	private GeneroService gService;
	
	@Autowired
	private ServletContext  servletContext;

	@RequestMapping("/home")
	public String iniciar() {
		return "Home";
	}
	
	@RequestMapping("/novo_filme")
	public String novo(Model model,HttpSession session) {
		try {
			List<Genero> generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			return "CriarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	@RequestMapping("/criar_filme")
	public String criarFilme(Filme filme, BindingResult erros, Model model,
			@RequestParam("posterPath2") MultipartFile posterPath) {
		try {
				Genero genero = new Genero();
				genero.setId(filme.getGenero().getId());
				genero.setNome(gService.buscarGenero(genero.getId()).getNome());
				filme.setGenero(genero);
				fService.gravarImagem(servletContext, filme, posterPath);
				filme = fService.inserirFilme(filme);
				model.addAttribute("filme", filme);
				return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}


	
	@RequestMapping("/editar_filme/{id}")
	public String editarFilme(@PathVariable Integer id,Filme filme,Model model,BindingResult errors) throws IOException {
			
			filme = fService.buscarFilme(id);
			List<Genero> generos = gService.listarGeneros();
			model.addAttribute("filme",filme);
			model.addAttribute("generos",generos);
			return "EditarFilme";
	}
	
	
	@RequestMapping("/atualizar")
	public String atualizarFilme(Filme filme,Model model,
			@RequestParam("posterPath") MultipartFile file) throws IOException {
		
		fService.updateFilme(filme);
		fService.gravarImagem(servletContext, filme, file);
		filme = fService.buscarFilme(filme.getId());
		System.out.println(filme);
		model.addAttribute("Filme",filme);
		return "redirect:/visualizar_filme/"+ filme.getId();
		
	}
	
	@RequestMapping("/excluir_filme")
	public String excluirFilme(Integer id) throws IOException {
		fService.excluirFilme(id);
		return "redirect:/listar_filmes";
	}
	
	
	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarFilmes";
	}
	
	@RequestMapping("/visualizar_filme/{id}")
	public String visualizarLista(@PathVariable Integer id,Filme filme, Model model) throws IOException {
		filme = fService.buscarFilme(id);
		model.addAttribute("filme",filme);
		return "VisualizarFilme";
	}
	
	

	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpSession session, Model model, String chave) {
		try {
			// HttpSession session = ((HttpServletRequest) model).getSession();

			List<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/generos")
	public String porGeneros(Model model) throws IOException {
		ArrayList <Genero> porGeneros = gService.listaGenFilmes();
		model.addAttribute("porGeneros",porGeneros);
		return "Generos";
	}
	
	@RequestMapping("/popularidade")
	public String porPopularidade(Model model) throws IOException {
		List<Filme> filmes1 = fService.listarPopulares(0.0,30.0);
		List<Filme> filmes2 = fService.listarPopulares(31.0,50.0);
		List<Filme> filmes3 = fService.listarPopulares(51.0,60.0);
		List<Filme> filmes4 = fService.listarPopulares(61.0,80.0);
		List<Filme> filmes5 = fService.listarPopulares(81.0,100.0);
		model.addAttribute("filmes1",filmes1);
		model.addAttribute("filmes2",filmes2);
		model.addAttribute("filmes3",filmes3);
		model.addAttribute("filmes4",filmes4);
		model.addAttribute("filmes5",filmes5);
		return "Popularidade";
	}
	
	@RequestMapping("/dtLancamentos")
	public String porDtLancamento(Model model) throws IOException {
		
		List<Filme> filmesAno = fService.porData("ano",1);
		List<Filme> filmesPenultimo = fService.porData("ano",2);
		List<Filme> filmesMes = fService.porData("mes",1);
		
		model.addAttribute("filmesAno",filmesAno);
		model.addAttribute("filmesMes",filmesMes);
		model.addAttribute("filmesPenultimo",filmesPenultimo);
		return "DataLancamento";
	}
	
	@RequestMapping("/carregar_filmes")
	public String carregarFilmes() {
		try {
			fService.carregarFilmes();
			return "redirect:listar_filmes"; 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Erro"; 
	}
}
