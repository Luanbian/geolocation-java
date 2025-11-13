package dev.useCode.geolocation.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.useCode.geolocation.domain.entities.Location;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

}
