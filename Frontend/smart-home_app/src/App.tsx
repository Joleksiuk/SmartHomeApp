import './App.css';
import ComponentsGrid from './components/ComponentsGrid';
import Sidebar from './components/Sidebar';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <div>
          <Sidebar/>
          <ComponentsGrid/>
        </div>

      </header>
    </div>
  );
}

export default App;
