package com.sizzle.server.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
	private final org.modelmapper.ModelMapper orgModelMapper;

	public ModelMapper() {
		this.orgModelMapper = new org.modelmapper.ModelMapper();
		orgModelMapper.getConfiguration().setAmbiguityIgnored(false);
		orgModelMapper.getConfiguration().setSkipNullEnabled(true);
	}

	public <D, T> D map(final T source, Class<D> destinationType) {
		return orgModelMapper.map(source, destinationType);
	}

	public <D, T> List<D> map(final Iterable<T> sourceIterable, Class<D> destinationType) {
		List<T> list = StreamSupport.stream(sourceIterable.spliterator(), false).toList();
		return list.stream().map(source -> map(source, destinationType)).collect(Collectors.toList());
	}

	public <D, T> List<D> map(final Collection<T> sourceIterable, Class<D> destinationType) {
		return sourceIterable.stream().map(entity -> map(entity, destinationType)).collect(Collectors.toList());
	}

	public <D, T> Page<D> map(final Page<T> sourcePage, Class<D> destinationType) {
		return sourcePage.map(p -> map(p, destinationType));
	}

	public <D, T> void map(final T source, final D destination) {
		orgModelMapper.map(source, destination);
	}
}
