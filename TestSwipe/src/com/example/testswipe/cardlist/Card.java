package com.example.testswipe.cardlist;

import java.io.File;
import java.util.Date;

public class Card {
	private int vote;
	private String subject;
	private String description;
	private Date due;
	private File attachment;
	
	public Card(int i, String subject){
		this.vote = i;
		this.subject = subject;		
	}
	public String getVote() {
		return String.valueOf(vote);
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	
}
