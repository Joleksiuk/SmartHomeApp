import { useEffect, useState } from 'react'
import {Button, Grid, Typography} from '@mui/material';
import ShellyForm from './ShellyForm';
import TuyaForm from './TuyaForm';
import ShellyService from '../../Services/ShellyService';
import TuyaService, { TuyaUser } from '../../Services/TuyaService';

export default function ConfigureAccounts() {

    const [shellyUserExists, setShellyUserExists]=useState<boolean>(false)
    const [tuyaUserExists, setTuyaUserExists]=useState<boolean>(false)
    
    useEffect(() => {

        var shellyUser = ShellyService.initShellyUser()
        if( shellyUser!==null){
            console.log('Shelly user exists') 
            setShellyUserExists(true)
        }
        else{
            console.log("Shelly user does not exist")
        }

        let tuyaUser = TuyaService.getTuyaUser()
        if(tuyaUser!==null){    
            console.log('Tuya user exists') 
            setTuyaUserExists(true)
        }
        else{
            console.log("Tuya user does not exist")
        }
      }, []);


    return (

      <Grid container direction="row" justifyContent="flex-end">  
          {shellyUserExists ?
          <div>
            <Typography>Shelly user already configured</Typography>
            <Button>Reset</Button>
          </div>
          :
            <ShellyForm/>
            }
          {tuyaUserExists ?
          <div>
            <Typography>Tuya user already configured</Typography>
            <Button>Reset</Button>
          </div>
          :
            <TuyaForm/> 
          }
             
      </Grid>
      );
}
