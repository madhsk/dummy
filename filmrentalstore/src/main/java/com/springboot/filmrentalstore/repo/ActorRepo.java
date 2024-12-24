package com.springboot.filmrentalstore.repo;

import com.springboot.filmrentalstore.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {
	List<Actor> findByLastName(String lastName);

	List<Actor> findByFirstName(String firstName);

}
