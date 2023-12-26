package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@Repository
public interface ExpeditionRepository extends JpaRepository<Expedition, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM create_expedition(:sourceStationId, :destinationStationId, :spaceshipId, :commanderId)", nativeQuery = true)
    Integer createExpedition(
            @Param("sourceStationId") Integer sourceStationId,
            @Param("destinationStationId") Integer destinationStationId,
            @Param("spaceshipId") Integer spaceshipId,
            @Param("commanderId") Integer commanderId
    );

    @Transactional
    @Query(value = "SELECT * FROM add_researcher_to_expedition(:researcherId, :expeditionId)", nativeQuery = true)
    void addResearcherToExpedition(
            @Param("researcherId") Integer researcherId,
            @Param("expeditionId") Integer expeditionId
    );

    @Transactional
    @Query(value = "SELECT * FROM set_expedition_completed(:expeditionId)", nativeQuery = true)
    void setExpeditionCompleted(@Param("expeditionId") int expeditionId);

    @Transactional
    @Query(value = """
                SELECT DISTINCT e.*
                FROM expeditions e
                JOIN expeditionresearchers r ON e.expedition_id = r.expedition_id
                WHERE r.researcher_id = :researcherId
            """, nativeQuery = true)
    List<Expedition> findExpeditionsByResearcherId(@Param("researcherId") Integer researcherId);

    @Transactional
    @Query(value = "SELECT * FROM expeditions e WHERE e.expedition_id = :expeditionId", nativeQuery = true)
    Optional<Expedition> findExpeditionById(@Param("expeditionId") Integer expeditionId);
}

