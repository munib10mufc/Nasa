package com.nasa.eonet.nasa.beans.success;

import java.util.List;

import com.nasa.eonet.nasa.beans.external.Event;

public class DisasterInquiryResponse extends SuccessResponse {

	private String title;
	private String description;
	private String link;
	private List<Event> events;

	public DisasterInquiryResponse(String respCode, String respDesc) {
		super(respCode, respDesc);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "DisasterInquiryResponse [title=" + title + ", description=" + description + ", link=" + link
				+ ", events=" + events + "]";
	}

}
