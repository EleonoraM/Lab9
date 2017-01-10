package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.porto.model.Articolo;
import it.polito.tdp.porto.model.Autore;
import it.polito.tdp.porto.model.AutoreDi;

public class PortoDAO {
	
	
	public List<Autore> getAllAutori() {
		List<Autore> autori = new ArrayList<Autore>();
		

		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM creator ";

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				autori.add(new Autore(rs.getInt("id_creator"),rs.getString("family_name"),rs.getString("given_name")));
			}

			conn.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	

		return autori;
	}
			
			public Set<AutoreDi> getAllCoautori(Autore a) {
		Set<AutoreDi> autori = new HashSet<AutoreDi>();
		

		try {
			Connection conn = DBConnect.getConnection();
			String sql = "	select  authorship.id_creator,authorship.eprintid from (select * from authorship where authorship.id_creator=?) as tab, authorship where tab.eprintid=authorship.eprintid";

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a.getCodice());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				autori.add(new AutoreDi(rs.getInt("id_creator"),rs.getLong("eprintid")));
			}

			conn.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	

		return autori;
	}
			

			public List<Articolo> getAllArticoli() {
				List<Articolo> autori = new ArrayList<Articolo>();
				

				try {
					Connection conn = DBConnect.getConnection();
					String sql = "SELECT * FROM article ";

					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();

					while (rs.next()) {
						
						autori.add(new Articolo(rs.getLong("eprintid"),rs.getInt("year"),rs.getString("title")));
					}

					conn.close();
					rs.close();

				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			

				return autori;
			}
					
			public static void main(String[] args) {
PortoDAO dao = new PortoDAO ();
List<Articolo> articoli= dao.getAllArticoli();
System.out.println(articoli);}
}
