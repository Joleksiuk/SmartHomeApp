import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import SettingsIcon from '@mui/icons-material/Settings';
import { Link } from 'react-router-dom';
import HomeIcon from '@mui/icons-material/Home';
import AppShortcutIcon from '@mui/icons-material/AppShortcut';

export const mainListItems = (
  <React.Fragment>
     <Link to="/"> 
      <ListItemButton>
        <ListItemIcon>
          <HomeIcon />
        </ListItemIcon>
        <ListItemText primary="HomePage" />
      </ListItemButton>
    </Link>
    <Link to="/configAccounts"> 
    <ListItemButton>
      <ListItemIcon>
        <SettingsIcon />
      </ListItemIcon>
      <ListItemText primary="Configure accounts" />
    </ListItemButton>
    </Link>
    <Link to="/manageHomes"> 
    <ListItemButton>
      <ListItemIcon>
        <HomeIcon />
      </ListItemIcon>
      <ListItemText primary="Homes" />
    </ListItemButton>
    </Link>
  </React.Fragment>
  

);

export const secondaryListItems = (
  <React.Fragment>
  
  </React.Fragment>
);
