package com.zeng.ezsh.chat.entity;

import org.apache.commons.net.ntp.TimeStamp;

public class Message {

	private int messageId;
	private int fromId;
	private String fromName;
//	private int toId;
	private String messageText;
	private TimeStamp messageDate;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public TimeStamp getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(TimeStamp messageDate) {
		this.messageDate = messageDate;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", fromId=" + fromId
				+ ", fromName=" + fromName + ", messageText=" + messageText
				+ ", messageDate=" + messageDate + "]";
	}
	
	
}
