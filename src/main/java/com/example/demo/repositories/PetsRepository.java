package main.java.com.example.demo.repositories;
import main.java.com.example.demo.models.Pets;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface PetsRepository extends MongoRepository<Pets, String> {
  Pets findBy_id(ObjectId _id);
}