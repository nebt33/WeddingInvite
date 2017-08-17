package com.example.WeddingInvite.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeddingInvite.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String>{
	//Integer countByUserName(String userName);
}
