package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.ActivitiesPlan;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.WaitOnAccessToActivity;

import javax.transaction.Transactional;
import java.util.List;

public interface WaitOnAccessToActivityRepository extends JpaRepository<WaitOnAccessToActivity, Long> {
    @Query("from WaitOnAccessToActivity w where w.activityPlan = ?1 AND w.userDetails = ?2")
    WaitOnAccessToActivity validateContainInList(ActivitiesPlan activitiesPlan, UserDetails userDetails);
    WaitOnAccessToActivity findByActivityPlan_Id(long id);
    @Query("select w.userDetails from WaitOnAccessToActivity w where w.activityPlan.id = ?1")
    List<UserDetails> allWaitingUsersInActivity(long id);
    @Modifying
    @Transactional
    @Query("delete WaitOnAccessToActivity w where w.activityPlan.id = ?1 and w.userDetails.id = ?2")
    void deleteFromWaitingList(long activitiesPlanId, long userDetailsId);
}
