package com.example.backend_bd;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final List<Evento> eventos = new ArrayList<>();

    public EventoController() {
        Categoria torneos = new Categoria("Torneos");
        Categoria expo = new Categoria("Exposición");
        Categoria meetup = new Categoria("Meetup");

        eventos.add(new Evento(
                1,
                "GamerFest 2025",
                torneos,
                "2025-05-10",
                "Santiago",
                Arrays.asList("League of Legends", "Valorant"),
                "Gran torneo gamer de otoño",
                "Av. Siempre Viva 742",
                "16:00",
                "Comunidad LOL Chile",
                Arrays.asList("Estacionamiento", "Comida", "Streaming"),
                Arrays.asList("Riot Games", "HyperX")
        ));

        eventos.add(new Evento(
                2,
                "Expo Indie Games",
                expo,
                "2025-06-15",
                "Valparaíso",
                Arrays.asList("Juegos Indie", "Pixel Art"),
                "Exposición de desarrolladores indie",
                "Calle Pixel 808",
                "11:00",
                "Indie Devs Latam",
                Arrays.asList("Cafetería", "Alojamiento"),
                Arrays.asList("GameDevChileno", "PixelCon")
        ));

        eventos.add(new Evento(
                3,
                "Reunión RetroGamers",
                meetup,
                "2025-07-20",
                "Concepción",
                Arrays.asList("Super Nintendo", "Arcade"),
                "Encuentro entre fans de consolas retro",
                "Plaza Retro 123",
                "14:00",
                "RetroCL",
                Arrays.asList("Locomoción", "Comida"),
                Arrays.asList("RetroGameStore")
        ));

        eventos.add(new Evento(
        4,
        "LAN Party Nocturna",
        torneos,
        "2025-08-12",
        "Antofagasta",
        Arrays.asList("CS:GO", "Starcraft"),
        "Competencia y comunidad hasta el amanecer",
        "Centro Juvenil Digital",
        "20:00",
        "AntofaGamers",
        Arrays.asList("Comida", "Estacionamiento"),
        Arrays.asList("Intel", "Razer")
        ));

        eventos.add(new Evento(
                5,
                "Cosplay & Games Fest",
                expo,
                "2025-09-05",
                "La Serena",
                Arrays.asList("Overwatch", "Zelda"),
                "Feria de cultura gamer y concurso de cosplay",
                "Parque Japonés",
                "10:00",
                "Serena Cosplay",
                Arrays.asList("Estacionamiento", "Almuerzo", "Camerinos"),
                Arrays.asList("Nintendo", "Blizzard")
        ));

        eventos.add(new Evento(
                6,
                "Indie Dev Camp",
                meetup,
                "2025-10-10",
                "Puerto Montt",
                Arrays.asList("Unity", "Godot"),
                "Encuentro de desarrolladores y workshops",
                "Cowork Austral",
                "09:00",
                "Sur Indie Devs",
                Arrays.asList("Wifi", "Café", "Charlas"),
                Arrays.asList("Playsystem", "DevSurHub")
        ));

        eventos.add(new Evento(
                7,
                "Retro Arcade Night",
                meetup,
                "2025-10-25",
                "Rancagua",
                Arrays.asList("Pac-Man", "Mortal Kombat"),
                "Jornada retro con torneos de arcade clásicos",
                "Centro Cultural Rancagua",
                "18:00",
                "Retro Lovers",
                Arrays.asList("Bebidas", "Arcade Free Play"),
                Arrays.asList("Museo Gamer", "LocalTech")
        ));

        eventos.add(new Evento(
                8,
                "VR Revolution",
                expo,
                "2025-11-18",
                "Viña del Mar",
                Arrays.asList("Beat Saber", "Half-Life: Alyx"),
                "Feria de realidad virtual con demos inmersivos",
                "Centro VR Viña",
                "12:00",
                "GamerTech",
                Arrays.asList("VR Demo", "Comida", "Alojamiento"),
                Arrays.asList("Meta", "SteamVR")
        ));

        eventos.add(new Evento(
                9,
                "Game Jam Extrema",
                torneos,
                "2025-12-02",
                "Temuco",
                Arrays.asList("Game Design", "Pixel Art"),
                "48 horas para crear el mejor videojuego",
                "Universidad de la Frontera",
                "08:00",
                "Temuco Jammers",
                Arrays.asList("Alojamiento", "Comida", "Mentorías"),
                Arrays.asList("Unity", "DuocUC")
        ));

    }

    // [Pública] Listar todos los eventos
    @GetMapping
    public List<Evento> getEventos() {
        return eventos;
    }

    // [Pública] Buscar eventos por múltiples criterios (nombre, categoría, fecha, ubicación, juego)
    @GetMapping("/buscar")
    public List<Evento> buscarEventos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) String juego
    ) {
        return eventos.stream()
                .filter(e -> nombre == null || e.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(e -> categoria == null || e.getCategoria().getNombre().equalsIgnoreCase(categoria))
                .filter(e -> fecha == null || e.getFecha().equals(fecha))
                .filter(e -> ubicacion == null || e.getUbicacion().toLowerCase().contains(ubicacion.toLowerCase()))
                .filter(e -> juego == null || e.getJuegosRelacionados().stream()
                        .anyMatch(j -> j.toLowerCase().contains(juego.toLowerCase())))
                .toList();
    }

    // [Privada] Ver detalle de un evento por ID
    @GetMapping("/{id}")
    public Evento getEvento(@PathVariable Integer id) {
        Optional<Evento> evento = eventos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        return evento.orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
}
