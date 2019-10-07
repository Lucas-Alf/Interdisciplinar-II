function lista() {
    bootbox.confirm({
        size: "large",
        title: "Lista",
        centerVertical: true,
        message: `
        <p:dataTable var="item" value="#{usuarioBean.Lista}">
            <p:column headerText="Id">
                <h:outputText value="#{item.id}" />
            </p:column>
        
            <p:column headerText="Email">
                <h:outputText value="#{item.email}" />
            </p:column>
        
            <p:column headerText="Senha">
                <h:outputText value="#{item.senha}" />
            </p:column>
        </p:dataTable>
        `,
        callback: () => {

        }
    });
}

function pesquisar(e) {
    if (e == undefined || e.keyCode == 13) {
        $('#dataTable').DataTable().ajax.url('/Usuario/ListarPaginado?email=' + $('#searchInputTextField').val()).load();
    }
}

function incluir() {
    bootbox.confirm({
        size: "large",
        title: "Incluir",
        centerVertical: true,
        message: `
        <form id="formUsuario">
            <div class="form-group row">
                <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="Email">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">Senha</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword" placeholder="Senha", name="Senha">
                </div>
            </div>
        <form>`,
        callback: function (confirma) {
            if (confirma) {
                $.ajax({
                    method: "POST",
                    url: "/Usuario/Incluir",
                    data: unifyArray($('#formUsuario').serializeArray()),
                    success: (x) => {
                        pesquisar();
                    },
                    error: (x) => { console.log(x) }
                })
            }
        }
    });
}