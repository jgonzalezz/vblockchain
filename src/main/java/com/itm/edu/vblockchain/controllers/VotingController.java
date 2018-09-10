package com.itm.edu.vblockchain.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itm.edu.vblockchain.models.entity.Voting;
import com.itm.edu.vblockchain.models.service.IVotingService;
import com.itm.edu.vblockchain.util.paginator.PageRender;

@Controller
@SessionAttributes("voting")
public class VotingController {

	@Autowired
	private IVotingService votingService;
	
	
	@RequestMapping(value = "/create-voting", method = RequestMethod.GET)
	public String createVoting(Map<String, Object> model) {
		
		Voting voting = new Voting();
		model.put("voting", voting);
		return "create-voting";
	}
	

	@RequestMapping(value = "/create-voting", method = RequestMethod.POST)
	public String save(Voting voting, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "create-voting";
		}

		String mensajeFlash = (voting.getId() != null) ? "Elección editada con éxito!" : "Elección creada con éxito!";

		String dateString = voting.getStringDate();

		String[] parts = dateString.split("-");
		String stringDateStart = parts[0].trim();
		String stringDateEnd = parts[1].trim();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
		Date dateStart = null;
		Date dateEnd = null;
		try {
			dateStart = formatter.parse(stringDateStart);
			dateEnd = formatter.parse(stringDateEnd);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		voting.setDateStart(dateStart);
		voting.setDateEnd(dateEnd);

		votingService.save(voting);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:list-voting";

	}

	@RequestMapping(value = "/list-voting", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 4);

		Page<Voting> votings = votingService.findAll(pageRequest);

		PageRender<Voting> pageRender = new PageRender<Voting>("/list-voting", votings);
		//model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("votings", votings);
		model.addAttribute("page", pageRender);
		return "list-voting";
	}

}
