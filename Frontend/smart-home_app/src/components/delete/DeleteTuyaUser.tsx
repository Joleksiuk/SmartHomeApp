import Button  from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import { useEffect, useState } from 'react';
import { Grid } from '@mui/material';
import DeleteModal from './DeleteModal';
import { DeleteUserProps } from './DeleteShellyUser';
import TuyaService from '../../Services/TuyaService';

  
export default function DeleteHouse(props:DeleteUserProps) {

    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState<string>('');
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    
    const onDeleteUserClicked=()=>{
       if(props.userId!==undefined){
             setOpenDeleteModal(true);
             setDeleteMessage('Do you really want to reset tuya credentials?');
       }
    }

    const deleteTuyaUser=()=>{
        if(props.userId!=undefined)
            TuyaService.deleteTuyaUser(props.userId);
    }

    useEffect(() => {  
        if(props===undefined )
            return;    
        if(confirmDelete===true){
            deleteTuyaUser()
            setConfirmDelete(false);
        }
    }, [confirmDelete]);

    return (
        <Grid container justifyContent="center" item xs={12}>  
            <DeleteModal setDeleteConfirm={setConfirmDelete} open={openDeleteModal} message={deleteMessage} setModalOpen={setOpenDeleteModal}/>
            <Button variant="outlined"  onClick = {onDeleteUserClicked} startIcon={<DeleteIcon /> }>Reset Tuya credentials</Button>
        </Grid>
    );
}