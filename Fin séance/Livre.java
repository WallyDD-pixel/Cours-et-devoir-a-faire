import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlType(propOrder={"titre","auteurs", "editeur", "isbn", "enregistrement", "prix" })
public class Livre {
    private String langue = "", titre = "", id = "", isbn = "", editeur = "", paysEdition="";
    Date enregistrement = null;
    int prix = 0 ;
    List<String> auteurs ;

    public Livre() {
        auteurs = new ArrayList<>() ;
    }

    public Livre(String langue, String titre, String id, String isbn, String editeur, String paysEdition, Date enregistrement, int prix, List<String> auteurs) {
        this.langue = langue;
        this.titre = titre;
        this.id = id;
        this.isbn = isbn;
        this.editeur = editeur;
        this.paysEdition = paysEdition;
        this.enregistrement = enregistrement;
        this.prix = prix;
        this.auteurs = auteurs;
    }

    @XmlTransient
    public String getPaysEdition() {
        return paysEdition;
    }

    public void setPaysEdition(String paysEdition) {
        this.paysEdition = paysEdition;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "langue='" + langue + '\'' +
                ", titre='" + titre + '\'' +
                ", id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", payd édition='" + paysEdition + '\'' +
                ", editeur='" + editeur + '\'' +
                ", enregistrement=" + enregistrement +
                ", prix=" + prix +
                ", auteurs=" + auteurs +
                '}';
    }

    @XmlAttribute(name = "langage")
    public String getLangue() {
        return langue;
    }


    public void setLangue(String langue) {
        this.langue = langue;
    }

    @XmlElement
    public String getTitre() {
        return titre;
    }

     public void setTitre(String titre) {
        this.titre = titre;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    @XmlElement(name = "dateEnregistrement")
    public Date getEnregistrement() {
        return enregistrement;
    }

    public void setEnregistrement(Date enregistrement) {
        this.enregistrement = enregistrement;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @XmlElementWrapper(name="auteurs")
    @XmlElement(name="auteur")
    public List<String> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<String> auteurs) {
        this.auteurs = auteurs;
    }
}
