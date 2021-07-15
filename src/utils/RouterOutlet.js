import React from "react";
import ErrorNotFound from "../components/ErrorNotFound";
import TelaInicial from "../views/TelaInicial";

class RouterOutlet extends React.Component {
    render() {

        var tela = <ErrorNotFound />;
        if (window.location.pathname === '/') {
            window.location.pathname = '/projects'
        }


        if (window.location.pathname === '/projects') {
            tela = <TelaInicial />
        }

        return tela;
    }
}

export default RouterOutlet;