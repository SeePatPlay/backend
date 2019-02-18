package com.cmad.sandboxers.data;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cmad.sandboxers.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public User findByNameLike(String name);

	public User findByidAndNameLike(String cid, String name);

	//public User findByCid(String cid);

	//public void addByUser(User u);
}