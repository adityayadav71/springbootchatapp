package com.aditya.chatapp.entities;

import java.sql.Timestamp;

import com.aditya.chatapp.utils.Enums.MessageStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	public String id;
	
	@Column(name = "text")
	public String text;
	
	@Column(name = "sender")
	public String sender;
	
	@Column(name = "receiver")
	public String receiver;
	
	@Column(name = "timestamp")
	public Timestamp timestamp;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public MessageStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	
	public ChatMessage(String text, String sender, String receiver, Timestamp timestamp, MessageStatus status) {
		super();
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
		this.timestamp = timestamp;
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", text=" + text + ", sender=" + sender + ", receiver=" + receiver
				+ ", timestamp=" + timestamp + ", status=" + status + "]";
	}
}
