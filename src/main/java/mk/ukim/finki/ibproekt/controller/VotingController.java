package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.model.*;
import mk.ukim.finki.ibproekt.model.exceptions.CandidateDoesNotExists;
import mk.ukim.finki.ibproekt.service.BlockchainService;
import mk.ukim.finki.ibproekt.service.CandidateService;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/vote")
public class VotingController {

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoterService voterService;

    @GetMapping("/{id}")
    public String vote(@PathVariable Long id, HttpServletRequest request)
    {
        Candidate candidate = this.candidateService.findById(id)
                .orElseThrow(()->new CandidateDoesNotExists("Candidate does not exists"));
      Block b =  this.blockchainService.createBlock(candidate);
      blockchainService.addBlock(b);
      Voter v = (Voter) request.getSession().getAttribute("voter");
      v.setVoted(true);
      voterService.save(v);
      return "done";
    }

    @GetMapping("/countVotes")
    public String count(Model model)
    {
        VoteCount vc = this.blockchainService
                .sealVotes();
        model.addAttribute("map",vc);
        return "voteCount";
    }
}
