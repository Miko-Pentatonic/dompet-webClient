package com.dompet.spring.client.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DompetRequest implements Serializable {
	private Long id_dom;
	private String keterangan;
	private Date tanggal;
	private Double pendapatan = Double.valueOf(0);
	private Double pengeluaran = Double.valueOf(0);
}
