import Button  from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import { useEffect, useState } from 'react';
import { Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import DeleteModal from './DeleteModal';
import ShellyService from '../../Services/ShellyService';


export interface DeleteUserProps{
    userId?: number,
}
  
export default function DeleteHouse(props:DeleteUserProps) {

    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState<string>('');
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    const navigate = useNavigate();
    
    const onDeleteHouseClicked=()=>{
       if(props.userId!==undefined){
             setOpenDeleteModal(true);
             setDeleteMessage('Do you really want to reset shelly credentials?');
       }
    }

    const deleteShellyUser=()=>{
        if(props.userId!=undefined)
            ShellyService.deleteShellyUser(props.userId);
    }

    useEffect(() => {  
        if(props===undefined )
            return;    
        if(confirmDelete===true){
            deleteShellyUser()
            setConfirmDelete(false);
        }
    }, [confirmDelete]);

    return (
        <Grid container justifyContent="center" item xs={12}>  
            <DeleteModal setDeleteConfirm={setConfirmDelete} open={openDeleteModal} message={deleteMessage} setModalOpen={setOpenDeleteModal}/>
            <Button variant="outlined"  onClick = {onDeleteHouseClicked} startIcon={<DeleteIcon /> }>Reset Shelly credentials</Button>
        </Grid>
    );
}