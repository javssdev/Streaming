package com.tmx.dashboard.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int numbeitemsByPage;
	private int paginaActual;

	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<>();

		numbeitemsByPage = 6;
		totalPages = page.getTotalPages();
		paginaActual = page.getNumber() + 1;

		int desde;
		int hasta;
		if (totalPages <= numbeitemsByPage) {
			desde = 1;
			hasta = totalPages;
		} else {
			if (paginaActual <= numbeitemsByPage / 2) {
				desde = 1;
				hasta = numbeitemsByPage;
			} else if (paginaActual >= totalPages - numbeitemsByPage / 2) {
				desde = totalPages - numbeitemsByPage + 1;
				hasta = numbeitemsByPage;
			} else {
				desde = paginaActual - numbeitemsByPage / 2;
				hasta = numbeitemsByPage;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}

	}


	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
