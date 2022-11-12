package com.idplus.fresqueclimat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idplus.fresqueclimat.data.Resource
import com.idplus.fresqueclimat.data.ResourceDao
import kotlinx.coroutines.launch


class ResourceViewModel(val dao: ResourceDao) : ViewModel() {

    val resources = dao.getAll()

    fun seedFakeData() {
        viewModelScope.launch {

            // insert fake data for testing
            dao.insertAll(
                arrayOf(
                    Resource(1,
                        "Boutique",
                        "Jeux de cartes et papier expédiés chez vous (France uniquement), et jeux à imprimer dans toutes les langues",
                        "https://fresqueduclimat.org/boutique/"),

                    Resource(2,
                        "Mémo",
                        "Un site « couteau suisse » pour retrouver rapidement les infos et liens essentiels de chaque carte",
                        "https://fresqueduclimat.org/memo/fr-FR/langs"),

                    Resource(3,
                        "Guide d'autoformation",
                        "Les informations nécessaires à un.e animateur.rice de la Fresque du Climat qui vient de se former ou qui souhaite se former seul.e",
                        "https://fresqueduclimat.org/world-plat-guide-dautoformation/"),

                    Resource(4,
                        "Support de formation",
                        "La présentation diffusée lors des Formations à l’animation organisées par l’Association",
                        "https://fresqueduclimat.org/world-plat-support-de-formation/"),

                    Resource(5,
                        "Support de formation professionnelle",
                        "La présentation diffusée lors des Formations à l’animation professionnelles organisées par l’Association sur une journée",
                        "https://drive.google.com/file/d/1QczHIi8rjOAQTPTjPSO0omYMXP7nOtpI/view?usp=sharing"),

                    Resource(6,
                        "Tutoriel animation en ligne",
                        "Les informations pour animer la version en ligne de la Fresque du Climat",
                        "https://fresqueduclimat.org/world-plat-tutoriel-animation-en-ligne/"),

                    Resource(7,
                        "MOOC",
                        "L’animation aux publics adultes et juniors expliquée par Cédric Ringenbach",
                        "https://fresqueduclimat.org/fr-fr-plat-mooc/"),

                    Resource(8,
                        "Interview de Cédric",
                        "Une vidéo pour expliquer d’où vient le projet, son objectif, sa stratégie, son mode de gouvernance et le positionnement de l’association",
                        "https://www.youtube.com/watch?v=DFWAeZdq4mc&list=PLzgWEHB4qAJygqWST0ttmLdoaSSd1nqyE&index=1"),

                    Resource(9,
                        "Antisèche",
                        "La préparation et les repères pour animer un atelier",
                        "https://fresqueduclimat.org/world-plat-antiseche/"),

                    Resource(10,
                        "Vidéo de restitution",
                        "Un exemple de restitution de la Fresque par les ceintures bleues Alice et Charles",
                        "https://fresqueduclimat.org/world-plat-video-de-restitution/"),

                    Resource(11,
                        "Wiki",
                        "Des informations supplémentaires sur les cartes du jeu",
                        "https://fresqueduclimat.org/wiki/index.php?title=Portail:Accueil"),

                    Resource(12,
                        "Echanges sur le débrief",
                        "3 animateurrices expérimenté.e.s nous présentent en vidéo leurs debrief respectifs",
                        "https://drive.google.com/file/d/1vNAjKpG2TrAShi4SuylPX59ZIxd-clCv/view"),

                    Resource(13,
                        "Bibliothèque collaborative",
                        "Des ouvrages, vidéos et podcasts proposés par la communauté",
                        "https://docs.google.com/spreadsheets/d/1CjdUmiSEjIeE6HtsqPWBpZH1_IT-fz7qFaFYyozV2tM/edit#gid=0"),

                    Resource(14,
                        "Outils de communication",
                        "Logo sous différents formats, photos libres de droits, descriptions de l’association",
                        "https://drive.google.com/drive/folders/1GyRrKWGaf2ST-Ee04KqjtmBrW-nebFjd"),

                    Resource(15,
                        "Boucles de discussion",
                        "Pour discuter entre animateur.rice.s de la même région, échanger sur les actus climat, et aussi faire quelques blagues !",
                        "https://docs.google.com/spreadsheets/d/1Jum4IXHwYOiaSe0CSMumU5KC5EGDlYpfjfrnWWn4M-c/edit?usp=sharing"),

                    Resource(16,
                        "Fresque d'entraînement",
                        "Une Fresque de poche pour travailler les liens de causalité",
                        "https://drive.google.com/file/d/1pNlHv1lAoxp5LICv869YHM876t4Du1_O/view?usp=share_link")
                )
            )
        }
    }
}
