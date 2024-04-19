package auto.profit.controllers;

import auto.profit.models.Car;
import auto.profit.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String cars(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("cars", carService.listCars(title));
        return "index";
    }

    @GetMapping("/car/{id}")
    public String carInfo(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "car-info";
    }

    @PostMapping("/car/create")
    public String createCar(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                            @RequestParam("file3") MultipartFile file3, Car car) throws IOException {
        carService.saveCar(car, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
}
