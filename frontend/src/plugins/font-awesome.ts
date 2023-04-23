/* import the fontawesome core */
import { library } from "@fortawesome/fontawesome-svg-core";

/* add some free styles */
import { faTwitter } from "@fortawesome/free-brands-svg-icons";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";

library.add(faUserSecret);
library.add(faTwitter);

/* add some free icons */
import { fas } from "@fortawesome/free-solid-svg-icons";
import { faB } from "@fortawesome/free-solid-svg-icons";
library.add(fas);
library.add(faB);

/* import specific icons */
import { faHome } from "@fortawesome/free-solid-svg-icons/faHome";
import { faUser } from "@fortawesome/free-solid-svg-icons/faUser";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons/faUserPlus";
import { faSignInAlt } from "@fortawesome/free-solid-svg-icons/faSignInAlt";
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons/faSignOutAlt";

library.add(faHome);
library.add(faUser);
library.add(faUserPlus);
library.add(faSignInAlt);
library.add(faSignOutAlt);

