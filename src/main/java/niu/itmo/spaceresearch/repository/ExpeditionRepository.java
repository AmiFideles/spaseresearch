package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author amifideles
 */
@Repository
public interface ExpeditionRepository extends JpaRepository<Expedition, Integer> {

    @Modifying
    @Query(value = "SELECT create_expedition(:sourceStationId, :destinationStationId, :spaceshipId, :commanderId)", nativeQuery = true)
    Integer createExpedition(
            @Param("sourceStationId") Integer sourceStationId,
            @Param("destinationStationId") Integer destinationStationId,
            @Param("spaceshipId") Integer spaceshipId,
            @Param("commanderId") Integer commanderId
    );

    //    @Modifying
    @Query(value = "SELECT add_researcher_to_expedition(:researcherId, :expeditionId)", nativeQuery = true)
    void addResearcherToExpedition(
            @Param("researcherId") Integer researcherId,
            @Param("expeditionId") Integer expeditionId
    );

    @Query(value = """
        SELECT DISTINCT e.*
        FROM expeditions e
        JOIN expeditionresearchers r ON e.expedition_id = r.expedition_id
        WHERE r.researcher_id = :researcherId
    """, nativeQuery = true)
    List<Expedition> findExpeditionsByResearcherId(@Param("researcherId") Integer researcherId);
}

