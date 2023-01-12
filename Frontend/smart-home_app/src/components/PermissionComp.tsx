import {Alert,Box,Button,FormControl,FormControlLabel,FormLabel,Grid,LinearProgress,List,ListItem, ListItemText, ListSubheader,Radio,RadioGroup,Stack,Switch,Table,TableBody,TableCell,TableRow,TextField,Typography,} from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import AuthService from "../authorization/AuthService";
import { Device, DeviceDto, HouseUserDto, RolePermission, RolePermissionDto} from "../interfaces";
import { device_url, houseUser_url, permission_url } from "../urls";
  
export default function PermissionComponent() {
    
    const {id} = useParams()

    const [me, setMe] =useState<HouseUserDto>();
    const [devices, setDevices] = useState<DeviceDto[]>();

    const [loading, setLoading]=useState<Boolean>(false)

    useEffect(() => {
        fetchMe();   
        fetchDevices();
    }, []);

    const fetchDevices = async () => {
        try {
          const response = await axios.get(device_url+'/houseId='+id);
          setDevices(response.data);
          setLoading(true);

        } catch (error) {console.error(error); }
      };

    const fetchMe = async () => {
        try {
          const response = await  axios.get<HouseUserDto>(houseUser_url+"/userId="+AuthService.getLoggedUser().id +'/houseId=' + id);
          setMe(response.data);
        } catch (error) { console.error(error);}
      };

    const setNewPermission=(event:any, value:any, rp:RolePermission, role:string)=>{
            
        let canSee:string="true";
        let canControl:string="false";

        switch(value){
            case 'See':
                canSee='true'
                canControl='false'
                break;
            case 'Control':
                canSee='true'
                canControl='true'
                break;
        }

        let msg={
            'id':rp.id,
            'deviceId':rp.deviceId,
            'role':role,
            'canSee':canSee,
            'canControl':canControl
        }

        axios.put(permission_url,msg)
        .then((response) => {
            setLoading(false);       
            fetchDevices()
        })
        .catch((error) => console.log(error));
    }

    const generateRadio=(device:DeviceDto, role:string)=>{
        
        let rp = device?.permissions?.filter(per=>per.role==role)[0]
        let defaultRadio ='See';
        if(rp?.canControl=='true'){
            defaultRadio="Control"
        }

        if(me?.role==='Admin'){
            return (
                <FormControl>
                    <RadioGroup
                        aria-labelledby="demo-radio-buttons-group-label"
                        value={defaultRadio}
                        name="radio-buttons-group"
                        onChange={(e,value) => setNewPermission(e,value,rp,role)}
                    >
                        <FormControlLabel value="See" control={<Radio />} label="See" />
                        <FormControlLabel value="Control" control={<Radio />} label="Control" />
                    </RadioGroup>
                </FormControl>
            )
        }
        else{
            return (<Typography>{} </Typography>)
        }                
    }

    const generateTableRow = (device:DeviceDto) => {   
        return (       
            <TableRow key={device.id}>
                <TableCell>{device.name}</TableCell>
                <TableCell>{generateRadio(device,"Guest")}</TableCell>
                <TableCell>{generateRadio(device,"Resident")}</TableCell>
            </TableRow>
        )              
    }
    
    return (
    <div>
        {(loading) ? <> 
            <Grid container spacing={3}>
                <Grid justifyContent="center" container item xs={12}>
                    <Typography  variant="h6" >Set roles permission</Typography>
                </Grid>    
    
                <Grid justifyContent="center" container item xs={12}>
                    <Table>
                    
                        <TableBody>
                            <TableRow>
                                <TableCell><Typography>Device</Typography></TableCell>
                                <TableCell><Typography>Guest</Typography></TableCell>
                                <TableCell><Typography>Resident</Typography></TableCell>
                            </TableRow>
                            {devices?.map(device => (
                                generateTableRow(device)
                            ))}
                        </TableBody>
                    </Table>
                </Grid>
    
            </Grid>
            </> 
        :
            <Box sx={{ width: '100%' }}>
                <LinearProgress />
            </Box>
        }
    </div>
       
    
    );  
};
