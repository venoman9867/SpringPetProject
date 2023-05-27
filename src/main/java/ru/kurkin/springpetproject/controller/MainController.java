package ru.kurkin.springpetproject.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kurkin.springpetproject.dao.ArtistRepository;
import ru.kurkin.springpetproject.model.Artist;
import ru.kurkin.springpetproject.model.MyResponse;

@RestController
public class MainController {
    @Autowired
    private ArtistRepository repository;
    private Artist[] artists;
    @RequestMapping("/get")
    public Artist[] requestTopArtists(RestTemplate restTemplate) {
        String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=9b0534a7c5b61cf51979cb9efa2bee30&format=json";
        String response = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        MyResponse response1 = gson.fromJson(response, MyResponse.class);
        artists = response1.getArtists().getArtist();
        return artists;
    }
    @RequestMapping("/save")
    public String saveArtist(){
        for (Artist artist:artists){
            repository.save(artist);
        }
        return "Artist saved!";
    }
}
