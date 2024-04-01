package com.xenia.apptosupportpatientswithocd.data.modules_data

import com.xenia.apptosupportpatientswithocd.R
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.Module
import com.xenia.apptosupportpatientswithocd.presentation.modules_screen.model.ModuleContent


val contentPerfectionism = listOf(ModuleContent("fff", "fff"))

val contentOCD = listOf(
    ModuleContent("Этиология и патогенез", "Этиология и патогенез заболевания или состояния (группы заболеваний или состояний)\n" +
            "На развитие ОКР оказывают влияние как генетические факторы, так и факторы окружающей среды. Многочисленные исследования подтверждают участие кортико-стриато-таламо-кортикальной системы (КСТКС) в патофизиологии расстройства. Другие отделы головного мозга также участвуют в патогенезе ОКР.\n" +
            "Близнецовые исследования и исследования семей с ОКР подтвердили наличие большого наследственного компонента в этиопатогенезе ОКР. При этом больший вес генетических факторов был отмечен при манифесте ОКР в детском возрасте, чем во взрослом. Конкретные гены и группы генов, влияющие на вероятность развития ОКР, неизвестны, хотя исследования в этой области продолжаются.\n" +
            "На вероятность развития ОКР, по-видимому, влияют несколько факторов окружающей среды, но причинно-следственные связи на данный момент надежно не верифицированы. В качестве подобных факторов можно выделить следующие....."),
    ModuleContent("Эпидемиология", "fff"),
    ModuleContent("Клиническая картина", "fff"),
    ModuleContent("Диагностика", "fff")
)

val contentAnxiety = listOf(ModuleContent("fff", "fff"))

val modulesList = listOf(
    Module(
        "Перфекционизм",
        R.drawable.main_screen_1,
        contentPerfectionism
    ),
    Module("ОКР",
        R.drawable.main_screen_1,
        contentOCD
    ),
    Module("Тревога",
        R.drawable.main_screen_1,
        contentAnxiety
    )
)