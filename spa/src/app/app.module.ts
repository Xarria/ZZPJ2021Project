import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { ScrollingModule } from '@angular/cdk/scrolling';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserPanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    ScrollingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
