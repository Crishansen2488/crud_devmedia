<div class="msgDiv">
	<div class="erroDiv"
		style="display: ${msgErro != null && msgErro != '' ? 'block' : 'none'}">
		${msgErro != null ? msgErro : ''}
	</div>
	<div class="sucessoDiv"
		style="display: ${msgSucessoCadastro != null && msgSucessoCadastro != '' ? 'block' : 'none'}">
		${msgSucessoCadastro != null ? msgSucessoCadastro : ''}
	</div>
	<div class="avisoDiv"
		style="display: ${msgAviso != null && msgAviso != '' ? 'block' : 'none'}">
		${msgAviso != null ? msgAviso : ''}
	</div>
</div>