package auto.profit.services;

import auto.profit.models.Car;
import auto.profit.models.Image;
import auto.profit.repositories.CarRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CarService {



    private final CarRepo carRepo;
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> listCars(String title) {
        if (title != null) return carRepo.findByTitle(title);
        return carRepo.findAll();
    }

    public void saveCar(Car car, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            car.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            car.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            car.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author: {}", car.getTitle(), car.getUsername());
        Car carFromDb = carRepo.save(car);
        carFromDb.setPreviewImageId(carFromDb.getImages().get(0).getId());
        carRepo.save(car);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }

    public Car getCarById(Long id) {
        return carRepo.findById(id).orElse(null);
    }
}
