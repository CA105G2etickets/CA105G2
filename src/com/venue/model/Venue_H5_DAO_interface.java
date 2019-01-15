package com.venue.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.event.model.Event_H5_VO;

public interface Venue_H5_DAO_interface {
	public void insert (Venue_H5_VO venueVO);
	public void update (Venue_H5_VO venueVO);
	public void delete (String venue_no);
	public Venue_H5_VO findByPrimaryKey (String venue_no);
	public List<Venue_H5_VO> getAll();	
	
	public Set<Event_H5_VO> getEventsByVenueNo(String venue_no);
}
