package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.davidfz.fresqueclimat.data.local.dao.ResourceDao
import com.davidfz.fresqueclimat.data.local.entities.Resource
import javax.inject.Inject

class ResourcesRepositoryImpl @Inject constructor(
    private val dao: ResourceDao
)
    : ResourcesRepository {

    override fun observeAllResources(): LiveData<List<Resource>> = dao.getAll()

    override suspend fun refreshResources() {
        dao.insertAll(
            arrayOf(
                Resource(1,
                    "Boutique",
                    "Jeux de cartes et papier expédiés chez vous (France uniquement), et jeux à imprimer dans toutes les langues",
                    "https://fresqueduclimat.org/boutique/"),

                Resource(2,
                    "Mémo",
                    "Un site « couteau suisse » pour retrouver rapidement les infos et liens essentiels de chaque carte",
                    ""),

                Resource(3,
                    "Guide d'autoformation",
                    "Les informations nécessaires à un.e animateur.rice de la Fresque du Climat qui vient de se former ou qui souhaite se former seul.e",
                    ""),

                Resource(4,
                    "Support de formation",
                    "La présentation diffusée lors des Formations à l’animation organisées par l’Association",
                    ""),

                Resource(5,
                    "Support de formation professionnelle",
                    "La présentation diffusée lors des Formations à l’animation professionnelles organisées par l’Association sur une journée",
                    ""),

                Resource(6,
                    "Tutoriel animation en ligne",
                    "Les informations pour animer la version en ligne de la Fresque du Climat",
                    ""),

                Resource(7,
                    "MOOC",
                    "L’animation aux publics adultes et juniors expliquée par Cédric Ringenbach",
                    ""),

                Resource(8,
                    "Interview de Cédric",
                    "Une vidéo pour expliquer d’où vient le projet, son objectif, sa stratégie, son mode de gouvernance et le positionnement de l’association",
                    ""),

                Resource(9,
                    "Antisèche",
                    "La préparation et les repères pour animer un atelier",
                    ""),

                Resource(10,
                    "Vidéo de restitution",
                    "Un exemple de restitution de la Fresque par les ceintures bleues Alice et Charles",
                    ""),

                Resource(11,
                    "Wiki",
                    "Des informations supplémentaires sur les cartes du jeu",
                    ""),

                Resource(12,
                    "Echanges sur le débrief",
                    "3 animateurrices expérimenté.e.s nous présentent en vidéo leurs debrief respectifs",
                    ""),

                Resource(13,
                    "Bibliothèque collaborative",
                    "Des ouvrages, vidéos et podcasts proposés par la communauté",
                    ""),

                Resource(14,
                    "Outils de communication",
                    "Logo sous différents formats, photos libres de droits, descriptions de l’association",
                    ""),

                Resource(15,
                    "Boucles de discussion",
                    "Pour discuter entre animateur.rice.s de la même région, échanger sur les actus climat, et aussi faire quelques blagues !",
                    ""),

                Resource(16,
                    "Fresque d'entraînement",
                    "Une Fresque de poche pour travailler les liens de causalité",
                    "")
            )
        )
    }
}