package main.application.service.manageAccountService;

import main.domain.converter.AmigoConverter;
import main.domain.resource.AmigoResource;
import main.domain.resource.PreviewPublicacion;
import main.persistence.IDs.IDamigo;
import main.persistence.entity.Amigo;
import main.persistence.entity.Usuario;
import main.persistence.repository.RepoAmigo;
import main.persistence.repository.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageFriendsImpl implements ManageFriends{


    private final AmigoConverter friendConverter = new AmigoConverter();

    @Autowired
    RepoUsuario repoUser;

    @Autowired
    RepoAmigo repoAmigo;

    @Override
    public AmigoResource addFriend(Integer id, String name) {

        Usuario user = repoUser.findByName(name);

        if(user == null) //comprobamos que el usuario existe
            return null;
        else{
            Amigo friend = new Amigo(id, user.getId());
            repoAmigo.save(friend);
            return friendConverter.convert(friend);
        }
    }

    @Override
    public AmigoResource removeFriend(Integer id, String name) {
        Usuario user = repoUser.findByName(name);

        if(user == null)//comprobamos que el usuario existe
            return null;
        else{
            Amigo friend = new Amigo(id, user.getId());
            repoAmigo.delete(friend);
            return friendConverter.convert(friend);
        }
    }

    @Override
    public List<PreviewPublicacion> viewPostOfFriend(Integer id, String name) {
        Usuario user = repoUser.findByName(name);

        if(user == null) //comprobamos que el usuario existe
            return null;
        else{
            Amigo friend = repoAmigo.findOne(new IDamigo(id, user.getId()));

            if(friend == null)//comprobamos que son amigos, sino, no podrá ver sus fotos
                return null;
            else{
                ViewImagesImpl viewImagesofFriend = new ViewImagesImpl();
                return viewImagesofFriend.viewPost(user.getId());
            }
        }
    }
}
