package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows" )
@NamedQueries({
	@NamedQuery(
		name = "getAllFollowee",
		query = "SELECT f FROM Follow As f WHERE f.followee = :followee AND f.follower = :me"
		),
	@NamedQuery(
		name = "getFolloweeCount",
		query = "SELECT COUNT(f) FROM Follow As f WHERE f.followee = :followee AND f.follower = :me"
		),
	@NamedQuery(
		name = "getMyFollowee",
		query = "SELECT r FROM Report As r WHERE r.employee in :followList ORDER BY r.id DESC"
		),
	@NamedQuery(
		name = "getMyFolloweeCount",
		query = "SELECT COUNT(r) FROM Report As r WHERE r.employee in :followList"
		),
	@NamedQuery(
		name = "getFollowList",
		query = "SELECT f.followee FROM Follow As f WHERE f.follower = :me"
		),
})
@Entity
public class Follow {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "follower_id", nullable = false)
	private Employee follower;

	@ManyToOne
	@JoinColumn(name = "followee_id", nullable = false)
	private Employee followee;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getFollower() {
		return follower;
	}

	public void setFollower(Employee follower) {
		this.follower = follower;
	}

	public Employee getFollowee() {
		return followee;
	}

	public void setFollowee(Employee followee) {
		this.followee = followee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
