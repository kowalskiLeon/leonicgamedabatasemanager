import logo from './logo.svg';
import './App.css';
import TelaInicial from './views/TelaInicial.js'
import MenuSuperior from './components/MenuSuperior';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Router, Route, Switch } from "react-router";
import RouterOutlet from './utils/RouterOutlet';


function App() {
  
  const visao = <div>
  <MenuSuperior />
  <RouterOutlet />
 </div>;
  return (visao);
}





export default App;
