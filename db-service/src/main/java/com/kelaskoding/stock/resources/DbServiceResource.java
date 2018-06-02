package com.kelaskoding.stock.resources;

import com.kelaskoding.stock.entity.Quote;
import com.kelaskoding.stock.entity.Quotes;
import com.kelaskoding.stock.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username){
       return getQuotesByUserName(username);
    }


    @PostMapping("/add")
    public List<String> add(@RequestBody Quotes quotes){

        quotes.getQuotes().stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote ->quoteRepository.save(quote));

        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") String username){
       List<Quote> quotes =  quoteRepository.findByUserName(username);
       quoteRepository.delete(quotes);
       return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(String username) {
        return quoteRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }


}
