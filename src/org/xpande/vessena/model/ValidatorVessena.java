package org.xpande.vessena.model;

import org.compiere.model.*;
import org.xpande.comercial.model.I_Z_PautaComVtaProd;
import org.xpande.comercial.model.MZPautaComVtaProd;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/10/21.
 */
public class ValidatorVessena implements ModelValidator {

    private int adClientID = 0;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {

        // Guardo compañia
        if (client != null){
            this.adClientID = client.get_ID();
        }

        // DB Validations
        engine.addModelChange(I_Z_PautaComVtaProd.Table_Name, this);

        // Document Validations

    }

    @Override
    public int getAD_Client_ID() {
        return this.adClientID;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (po.get_TableName().equalsIgnoreCase(I_Z_PautaComVtaProd.Table_Name)){
            return modelChange((MZPautaComVtaProd) po, type);
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }

    /***
     * Validaciones para el modelo de Producto de Pauta Comercial de Venta.
     * Xpande. Created by Gabriel Vila on 4/10/21.
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public String modelChange(MZPautaComVtaProd model, int type) throws Exception {

        if ((type == ModelValidator.TYPE_BEFORE_NEW) || (type == ModelValidator.TYPE_BEFORE_CHANGE)){

            MProduct product = (MProduct) model.getM_Product();
            if (product.get_ValueAsInt("Z_LineaProd_ID") > 0){
                model.set_ValueOfColumn("Z_LineaProd_ID", product.get_ValueAsInt("Z_LineaProd_ID"));
            }

            if (product.get_ValueAsInt("Z_GrupoProd_ID") > 0){
                model.set_ValueOfColumn("Z_GrupoProd_ID", product.get_ValueAsInt("Z_GrupoProd_ID"));
            }
        }

        return null;
    }

}
